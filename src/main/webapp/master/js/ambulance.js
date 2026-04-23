
function saveambulance(){
	var vehicleno = document.getElementById("vehicleno").value;
	var ambulancetype=document.getElementById("ambulancetype").value;
	var driverfname=document.getElementById("driverfname").value;

	
	
	
	 if(vehicleno==''){
      	jAlert('error', "please enter vehicle number!", 'Error Dialog');	
			setTimeout(function() {
				$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration); 	
			 return false;	
      	}
		else if(ambulancetype==''){
	      	jAlert('error', "please select Ambulance type!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
				 return false;	
	      	}
	 
		else if(driverfname==''){
	      	jAlert('error', "please select Driver name!", 'Error Dialog');	
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