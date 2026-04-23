function fileUpload1(form, action_url, div_id) {
	
	var docfile = document.getElementById('fileUpload').value;
	//var editdocfile = document.getElementById('editfUpload').value;
		
		if(docfile == ""){
		 jAlert('error', 'Please upload file !!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}
	
	else{
    // Create the iframe...
    var iframe = document.createElement("iframe");
     iframe.setAttribute("id", "upload_iframe");
    iframe.setAttribute("name", "upload_iframe");
    iframe.setAttribute("width", "0");H
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
                content = iframeId.contentDocument.body.innerHTML;
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
				}, alertmsgduration);
                
                document.getElementById('savebtn').disabled = '';
                document.getElementById('uploadbtn').disabled = 'disabled';
            } else if (iframeId.contentWindow) {
                content = iframeId.contentWindow.document.body.innerHTML;
               
            } else if (iframeId.document) {
                content = iframeId.document.body.innerHTML;
               
            }
 
            document.getElementById(div_id).innerHTML = content;
 
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
 
    document.getElementById(div_id).innerHTML = "Uploading...";
    //document.getElementById('uploaded').innerHTML = 'Uploaded';
   // document.getElementById('savebtn').disabled = '';
}
	return true;
}

function editfileUpload1(form, action_url, div_id) {
	
	//var docfile = document.getElementById('fileUpload').value;
	var editdocfile = document.getElementById('editfUpload').value;
	
	if(editdocfile == ""){
		 jAlert('error', 'Please upload file !!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}

else{
    // Create the iframe...
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
                content = iframeId.contentDocument.body.innerHTML;
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
				}, alertmsgduration);
                
            } else if (iframeId.contentWindow) {
                content = iframeId.contentWindow.document.body.innerHTML;
               
            } else if (iframeId.document) {
                content = iframeId.document.body.innerHTML;
               
            }
 
            document.getElementById(div_id).innerHTML = content;
 
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
 
    document.getElementById(div_id).innerHTML = "Uploading...";
    //document.getElementById('uploaded').innerHTML = 'Uploaded';
}
	return true;
}
//document.getElementById('uploaded').style.display = 'none';

function addDocument(id){
	
	document.getElementById('docList').style.display = 'none';
	document.getElementById('adddocument').style.display = '';
	document.getElementById('viewdoc').style.display = 'none';
	document.getElementById('editdoc').style.display = 'none';
	document.getElementById('docTitle').innerHTML = "Add Document";
	document.getElementById('adddoc').innerHTML = '';
	document.getElementById('savebtn').disabled = 'disabled';
	document.getElementById('uploadbtn').disabled = '';
	document.getElementById('errorReport').innerHTML="";
	document.getElementById('report').style.display = 'none';
    document.getElementById('reportdoc').style.display = 'none';
	
}

function closeAddDoc(){
	
	document.getElementById('docList').style.display = '';
	document.getElementById('adddocument').style.display = 'none';
	document.getElementById('viewdoc').style.display = 'none';
	document.getElementById('editdoc').style.display = 'none';
	document.getElementById('docTitle').innerHTML = 'Document';
	document.getElementById('adddoc').innerHTML = '<a style="color: white; " href="javascript:void(0)" onclick="addDocument(\'adddocument\')">Add</a>';
}

//edit Document

function editDocument(selectedid,divid){
	
	editdocid = selectedid;
	
	document.getElementById('docList').style.display = 'none';
	document.getElementById('editdoc').style.display = '';
	document.getElementById('adddocument').style.display = 'none';
	document.getElementById('viewdoc').style.display = 'none';		
	document.getElementById(divid).style.display = '';
	document.getElementById('docTitle').innerHTML = "Modify Document";
	document.getElementById('adddoc').innerHTML = '';
	document.getElementById('errorReport').innerHTML="";
	document.getElementById('report').style.display = 'none';
    document.getElementById('reportdoc').style.display = 'none';
	
  
  viewDocumentAjax(selectedid);
	
}

//document.getElementById('savebtn').disabled = 'disabled';
function saveDocAjax(){
	
	
	var doc = document.getElementById('docText').value;
	var apmtid = document.getElementById('selectedid').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	//var pid = document.getElementById('diaryUser').value;
	var docfile = document.getElementById('fileUpload').value;
		
	if(doc == "" && docfile == ""){
		 jAlert('error', 'Please upload file and add Desciption !!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else if(doc == ""){
		jAlert('error', 'Please add Desciption !!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		}
	else if(docfile == ""){
		jAlert('error', 'Please upload file !!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		}
	
	else{
	
	var url = "saveDocEmr?selectedPatientId="+selectedPatientId+"&doc="+doc+"&apmtid="+apmtid+"&pid="+pid+" ";

	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = saveDocAjaxRequest;
    req.open("GET", url, true); 
              
    req.send(null);
	}
	return true;
}

function resetField(){
	document.getElementById('docText').value = "";
	document.getElementById('editdoctext').value = "";
	document.getElementById('fileUpload').value = "";
}

function saveDocAjaxRequest(){
if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('docList').innerHTML = req.responseText;
			document.getElementById('docText').value = "";
			document.getElementById('fileUpload').value = "";
			closeAddDoc();
		}
}
}

//view and close Document
function viewDocument(selectedid,divid){
	
	document.getElementById('docList').style.display = 'none';
	document.getElementById('adddocument').style.display = 'none';
	document.getElementById('viewdoc').style.display = '';
	document.getElementById('editall').style.display = 'none';	
	document.getElementById(divid).style.display = '';
	document.getElementById('docTitle').innerHTML = "View Document";
	document.getElementById('adddoc').innerHTML = '';
	document.getElementById('errorReport').innerHTML="";
	document.getElementById('report').style.display = 'none';
    document.getElementById('reportdoc').style.display = 'none';
  
	viewDocumentAjax(selectedid);
	
}

function viewDocumentAjax(selectedid){
	
	var url = "viewDocumentEmr?selectedid="+selectedid+"";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = viewDocumentAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}

function viewDocumentAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			var data = req.responseText;
			var temp = data.split("/");
			var filename = temp[0];			
						
			var desc= temp[1];
			
						
			document.getElementById('viewdoctext').value = desc;
			document.getElementById('editfileUpload').value = filename;
			document.getElementById('editdoctext').value = desc;
			document.getElementById('editfileUpload').value = filename;
			document.getElementById('fname').innerHTML = filename;
			
		}
	}
}


//editsave Document

function editsaveDocAjax(){
	
	var doc = document.getElementById('editdoctext').value;
	var selectedPatientId = document.getElementById('selectedPatientId').value;
	var apmtid = document.getElementById('selectedid').value;
	
	
	var docfile = document.getElementById('fileUpload').file;
	if(doc == "" && docfile == ""){
		 jAlert('error', 'Please upload file and add Desciption !!', 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	}	
	else if(doc == ""){
		jAlert('error', 'Please add Desciption !!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		}
	else if(docfile == ""){
		jAlert('error', 'Please upload file !!', 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		}
	
	else{
		
	var name = null;
	if(document.getElementById('editfUpload').value!=""){
		
		name = document.getElementById('editfUpload').value;
	}
	if(document.getElementById('editfUpload').value==""){
		
		name = $('#fname').text();	
	}
	var url = "editsaveDocumentEmr?selectedid="+editdocid+"&doc="+doc+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&name="+name+"&apmtid="+apmtid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = editsaveDocAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
}
	return true;
}


function editsaveDocAjaxRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('docList').innerHTML = req.responseText;
			document.getElementById('editdoctext').value = "";
			document.getElementById('fileUpload').value = "";
			document.getElementById('editfUpload').value = "";
			document.getElementById('editfUpload').innerHTML = document.getElementById('editfUpload').innerHTML;
			closeAddDoc();
			
		}
	}
	
}



function deleteDocumentAjax(selectedid){
	
	var apmtid = document.getElementById('selectedid').value; 
	
	var r=confirm("Are you sure you want to delete this entry");
	if (r==true)
	  {	
	var url = "deleteDocumentEmr?selectedid="+selectedid+"&selectedPatientId="+selectedPatientId+"&pid="+pid+"&apmtid="+apmtid+" ";

	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = deleteDocumentAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	  }
	else
	  {
	  return false;
	  }
}

function deleteDocumentAjaxRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			document.getElementById('docList').innerHTML = req.responseText;
			closeAddDoc();
		}
	}
	
}
