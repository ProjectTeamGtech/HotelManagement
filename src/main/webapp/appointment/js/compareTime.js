var hh_hour = 0, tempHour = 0;
var mm_minue = 0, tempMinute = 0;
var hh_toHour = 0, toTempHour = 0;
var mm_toMinute = 0,toTempMinute = 0;


function compareHour(hour){
	hh_hour = hour;
	
	var practitionerid = document.getElementById('practitionerID').value;
	var location  = document.getElementById('location').value;
	var when = document.getElementById('when').value;
	
	var url = "hourAppointment?practitionerid="+practitionerid+"&location="+location+"&when="+when+" ";
   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = compareHourRequest;
    req.open("GET", url, true); 
              
    req.send(null);
}

function compareHourRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
		
			var starttime = req.responseText;
			var temp = starttime.split(":");
			
			tempHour = temp[0];
			tempMinute = temp[1];
			
			if(tempHour!=hh_hour){
				
                jAlert('error', 'Start Hour is not Valid.', 'Error Dialog');
            	setTimeout(function() {
					$("#popup_container").remove();
					removeAlertCss();
				}, alertmsgduration);

			}
			
		}
	}

}


function compareMinute(minute){
	mm_minue = minute;
		if(tempMinute!=mm_minue){
                jAlert('error', 'Start Minute is not Valid.', 'Error Dialog');

			}
}