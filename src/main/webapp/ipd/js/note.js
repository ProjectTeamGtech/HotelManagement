/**
 * 
 */
 
 var ipdaddmissionid = 0;
 
 function openipdrequestnote(val){

    $("#ipdrequestnote").modal('show');
    document.getElementById("actiontype").value=val;
    //counsultationcount(ipdaddmissionid);
    shownote(ipdaddmissionid);

}

function savereqnote(){
	var reqid=document.getElementById("reqid").value;
	var actiontype=document.getElementById("actionnote").value;
	var note=document.getElementById("reqnotes").value;
	var ispunchkarma=document.getElementById("actiontype").value;
	var procedureid=document.getElementById("otprocedureplaned").value;
	var dataObj={
		"note":""+note+"",
		"ipdaddmissionid":""+ipdaddmissionid+"",
		"actiontype":""+actiontype+"",
		"reqid":""+reqid+"",
		"ispunchkarma":""+ispunchkarma+"",
		"procedureid":""+procedureid+"",
	}
	var data1 = JSON.stringify(dataObj);
	$.ajax({
		url : "savenoteBookAppointmentAjax",
		data : data1,
		dataType : 'json',
		contentType : 'application/json',
		type : 'POST',
		async : true,
		success : function(responseData){
			var ipdid=responseData.ipdaddmissionid;
			shownote(ipdid);
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
	/* var url ="savenoteBookAppointmentAjax?note="+note+"&ipdaddmissionid="+ipdaddmissionid+"";
		
	    if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
		
        req.onreadystatechange = savesittingRequest;
		req.open("GET", url, true);
		req.send(null);
		*/
	
	
}


function savesittingRequest(){
	
	if (req.readyState == 4) {
		 if (req.status == 200) {
			var str = req.responseText;
			//document.getElementById('note').innerHTML = req.responseText;
			//$("#notedetails").modal('show');
		if (str == '0') {
			 alert("data not added.");                   // error
			} else {
				alert("Added Successfully.");
				//window.location.reload();
				$("#ipdrequestnote").modal('show');
                     // success
			}
			
         }
		
     }
	
  }
  
  
  function shownote(ipdadmissionid){
	
	var url = "requestnotelistNotAvailableSlot?ipdadmissionid="+ipdadmissionid+"";
	
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
			 //$( "#sitting_details" ).modal( "show" );		
		}
	}
	
}
  
  
function deleterequestnote(id){
	
	var dataObj={
		"id":""+id+"",
		"ipdadmissionid":""+ipdaddmissionid+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"deletenoteBookAppointmentAjax",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var ipdid=responseData.ipdaddmissionid;
			shownote(ipdid);
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
}

function deletesittingRequest(){
     	if (req.readyState == 4) {
		    if (req.status == 200) {
			  var str = req.responseText;
			   $("#baselayout1loaderPopup").modal("hide");
			
			if (confirm("Do you want to delete?") == true) {
                         "Data deleted successfully!";
                         //window.location.reload();
             } else {
                         " Cancelled!";
           }
		}
    }

}
 
function editSitting(id,actiontype){
	
		var dataObj={
		"id":""+id+"",
		"actiontype":""+actiontype+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"editnoteBookAppointmentAjax",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var reqnote=responseData.reqnote;
			var actiontype=responseData.actiontype;
			var id=responseData.id;
			document.getElementById("reqnotes").value=reqnote;
			document.getElementById("actionnote").value=actiontype;
			document.getElementById("reqid").value=id;
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
        
}

function seennote(val){
	
	
	$("#seeneuestpopup").modal('show');
	seenrequestnot(val);
	
}

function seenrequestnot(id){
	
	var dataObj={
		"id":""+id+"",
		"ipdadmissionid":""+ipdaddmissionid+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"seennoteBookAppointmentAjax",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var reqnote=responseData.reqnote;
			var ipdaddmissionid=responseData.ipdaddmissionid;
			document.getElementById("setnote").innerHTML=reqnote;
			shownote(ipdaddmissionid);
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
	//var url = "seennoteBookAppointmentAjax?id="+val+"";
	
	//if (window.XMLHttpRequest) {
		//req = new XMLHttpRequest();
	//}
	//else if (window.ActiveXObject) {
		//isIE = true;
		//req = new ("Microsoft.XMLHTTP");
	//}  
	
    //req.onreadystatechange = setseenrequestnoteRequest;
    //req.open("GET", url, true); 
    //req.send(null);
	
	
	
}

function setseenrequestnoteRequest(){

     if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  document.getElementById("setnote").innerHTML = str;
				
		 }
	}
			   
	}		   

function counsultationcount(ipdaddmissionid){
	
	var dataObj={
		"ipdadmissionid":""+ipdaddmissionid+"",
	    }
        var data1=JSON.stringify(dataObj);
        $.ajax({
	     url:"notecontFinder",
	     data:data1,
         dataType : 'json',
		 contentType : 'application/json',
		 type : 'POST',
		 async : true,
		success : function(responseData){
			var notecount=responseData.notecount;
			var ipdaddmissionid=responseData.ipdaddmissionid;
			document.getElementById("notecount").innerHTML= "("+notecount+")";
			//shownote(ipdaddmissionid);
		},
		error : function(result){
			jAlert('error',"Something wrong!",'Error Dialog');
			setTimeout(function(){
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
		}
	});
	
	//var url="notecontFinder?ipdaddmissionid="+ipdaddmissionid+"";
	
	
	//if (window.XMLHttpRequest) {
		//req = new XMLHttpRequest();
	//}
	///else if (window.ActiveXObject) {
		//isIE = true;
		//req = new ("Microsoft.XMLHTTP");
	//}  
    //req.onreadystatechange = setconsultcountRequest;
    //req.open("GET", url, true); 
   // req.send(null);
	
	
	
}

function setconsultcountRequest(){

if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			document.getElementById("notecount").innerHTML= "("+str+")";
	
		 }
	}

}

