/**
 * 
 */


function  addsitting(){
	
	
	var department=document.getElementById("id").value;
	var sittingFollowup=document.getElementById("sittingFollowup").value;
	
	if (department == '') {

		jAlert('error', "Please select department!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
         return false;
	}else if(sittingFollowup==''){
		
		jAlert('error', "Please enter sitting!", 'Error Dialog');
		setTimeout(function() {
			$("#popup_container").remove();
			removeAlertCss();
		}, alertmsgduration);
		
		return false;
		
	} 
	else {

		$("#baselayout1loaderPopup").modal("show");
		/*var url = "saveSitting?department="+department+"&sittingFollowup="+sittingFollowup+"";
		if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = savenewsittingRequest;
		req.open("GET", url, true);
		req.send(null);*/
        
		return true;
	}
	
}	


function savenewsittingRequest(){
	
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

 function  updatesitting(){
	 
	 
	    var department=document.getElementById("dept").value;
		var sittingFollowup=document.getElementById("sittingFollowup").value;
	 
	 
		if (department == '') {

			jAlert('error', "Please select department!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
	         return false;
		}else if(sittingFollowup==''){
			jAlert('error', "Please enter sitting!", 'Error Dialog');
			setTimeout(function() {
				$("#popup_container").remove();
				removeAlertCss();
			}, alertmsgduration);
			
			return false;
			
		} 
		else {
			
			$("#baselayout1loaderPopup").modal("show");
		     return true;
		}
	 
	 
	  }


