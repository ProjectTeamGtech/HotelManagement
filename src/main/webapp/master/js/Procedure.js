/**
 * 
 */


function  saveprocedure(){
	
	
	var proceduretype=document.getElementById("master_id").value;
	var procedurename=document.getElementById("procedureName").value;
	var sitting=document.getElementById("sitting_id").value;
	
	if (proceduretype == '') {

		jAlert('error', "Please select procedureTYPE!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}else if(procedurename==''){
		
		jAlert('error', "Please enter procedurename!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	} 
	else {

		$("#baselayout1loaderPopup").modal("show");
		var url = "saveProcedure?proceduretype="+proceduretype+"&procedurename="+procedurename+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = savenewprocedureRequest;
		req.open("GET", url, true);
		req.send(null);

	}
	
}	


function savenewprocedureRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
		  var str = req.responseText;
			$("#baselayout1loaderPopup").modal("hide");
			
			if (str == '0') {
				// error
				alert("data not added.");
			} else {
				alert("Added Successfully.");
				window.location.reload();

				// success
			}
		}
	}
}



function editprocedureDetails(id) {
	
	var url="editProcedure?id="+id+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = setprocedureRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function setprocedureRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  var data = str.split("~~");
			
			
			document.getElementById("proid").value= data[0];
			document.getElementById("proceid").className="";
			document.getElementById("proceid").value = data[1];
			document.getElementById("procedure").value = data[2];
			
			document.getElementById("proceid").className ="form-control chosen";
			 $("#proceid").trigger("chosen:updated");
		  	 $(".chosen").chosen({allow_single_deselect: true});
			$('#editprocedure').modal( "show" );	
		 }
	}
	
}

function updateprocedure(){
	
	var id=document.getElementById("proid").value;
	var proceduretype=document.getElementById("proceid").value;
	var procedurename=document.getElementById("procedure").value;
	
	
	if ( proceduretype== '') {

		jAlert('error', "Please select Procedure Type!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	}else if(procedurename==''){
		
		jAlert('error', "Please enter ProcedureName!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
	} 
	else{
	
	var url = "updateProcedure?id="+id+"&proceduretype="+proceduretype+"&procedurename="+procedurename+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updateProcedureRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}

}

function updateProcedureRequest() {
	
  if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			$('#editprocedure').modal( "hide" );
			window.location = 'Procedure';	
		 }
	}
	
}


function deleteProcedureData(id){
	
	var url = "deleteProcedure?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = deleteprocedureRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function deleteprocedureRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			$("#baselayout1loaderPopup").modal("hide");
			
			if (str == '0') {
				// error
				alert("error ");
			} else {
				alert("delete Successfully.");
				window.location.reload();

			 }
		}
    }
}



function setDepartmentsitting(id) {
	
	var url="getsittingdepartmentProcedure?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	
    req.onreadystatechange = setSittingofDepartmentRequest;
    req.open("GET", url, true); 
    req.send(null);

	}


function setSittingofDepartmentRequest(){
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			  document.getElementById("proddiv").innerHTML=req.responseText;
			   $("#sitting_id").trigger("chosen:updated");
		  	   $(".chosen").chosen({allow_single_deselect: true});
		  	  
         }
	}	 
	
}

function setproceduremaster(id){
	
	var url="masterlistProcedure?id="+id+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	
    req.onreadystatechange = setmasterlistRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function  setmasterlistRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			  document.getElementById("mas_div").innerHTML=req.responseText;
			   $("#master_id").trigger("chosen:updated");
		  	   $(".chosen").chosen({allow_single_deselect: true});
		  	  
         }
	}	 
	
}



/*function setProcedurelist(id){
	
	var url="getproceduremasterlistProcedure?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	} 
	
    req.onreadystatechange = setProcedurelistRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}


function  setProcedurelistRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			  document.getElementById("pro_dure").innerHTML=req.responseText;
			   $("#procedure_id").trigger("chosen:updated");
		  	   $(".chosen").chosen({allow_single_deselect: true});
		  	  
         }
	}	 
	
}

*/










