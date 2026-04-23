/**
 * 
 */


function  saveunit(){
	
	
	var unit=document.getElementById("unitMeasurement").value;
	
	if (unit == '') {

		jAlert('error', "Please enter unit!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} 

	else {

		$("#baselayout1loaderPopup").modal("show");
		var url = "saveUnit?unit=" + unit + "";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = savenewunitRequest;
		req.open("GET", url, true);
		req.send(null);

	}
	
}	
	
function savenewunitRequest(){
		
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
	
function editUnitDetails(id) {
	
	var url="editUnit?id="+id+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = setunitRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function setunitRequest() {
	
	if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			  var data = str.split("~~");
			
			
			document.getElementById("id").value= data[0];
			document.getElementById("unit").value = data[1];
		
			$('#editunit').modal( "show" );	
		 }
	}
	
}


function updateunit(){
	
	var id=document.getElementById("id").value;
	var unit=document.getElementById("unit").value;
	
	
	if (unit == '') {

		jAlert('error', "Please enter unit!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);

	} else{
	
	var url = "updateUnit?id="+id+"&unit="+unit+"";
	
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updateunitRequest;
    req.open("GET", url, true); 
    req.send(null);
	
	}

}

function updateunitRequest() {
	
  if (req.readyState == 4) {
		if (req.status == 200) {
			var str = req.responseText;
			$('#editunit').modal( "hide" );
			window.location = 'Unit';	
		 }
	}
	
}

function deleteUnitData(id){
	
	var url = "deleteUnit?id="+id+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ("Microsoft.XMLHTTP");
	}  
    req.onreadystatechange = deleteunitRequest;
    req.open("GET", url, true); 
    req.send(null);
	
}

function deleteunitRequest(){
	
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
