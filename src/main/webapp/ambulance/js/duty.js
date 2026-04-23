
function savedutyallocate(){
	
	var patient=document.getElementById("patient").value;
	var city=document.getElementById("city").value;
	var pickdrop=document.getElementById("pickdrop").value;
	var hosp=document.getElementById("hosp").value;
    var currdate=document.getElementById("currdate").value;
	
	 if(patient==''){
	      	jAlert('error', "please enter Patient Name!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
				 return false;	
	      	}
	
	 else if(city==''){
	      	jAlert('error', "please enter City!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
				 return false;	
	      	}
	 else if(pickdrop==''){
	      	jAlert('error', "please select pick and Drop Location!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
				 return false;	
	      	} 
	 else if(hosp==''){
	      	jAlert('error', "please enter Hospital Name!", 'Error Dialog');	
				setTimeout(function() {
					$("#popup_container").remove();
						removeAlertCss();
					}, alertmsgduration); 	
				 return false;	
	      	}
	 else if(currdate==''){
	      	jAlert('error', "please enter current date!", 'Error Dialog');	
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