function setApmtLocation(location){
	
	//document.getElementById('location').value = location;
}

function setCheckedAppointmentData(dataString){
	
	$( "#allAvailabilityPopup" ).modal( "show" );
	var temp = dataString.split("/");
	var slotid = temp[0];
	var diaryuserid = temp[1];
	var diaryuser = temp[2];
	var location = temp[3];
	var starttime = temp[4];
	var endtime = temp[5];
	var date = temp[6];
	
	slotstarttime = starttime;
	//etime = endtime;
	//document.getElementById('slotId').value = slotid;
	//document.getElementById('date').value = date;
	
	diaryUser1 = slotid;
	document.getElementById('slotId').value = slotid;
	
	var temdate = date.split("-");
	var changedate = temdate[2] + "/" + temdate[1] + "/" + temdate[0];
	
	var url = "showallAvailabilityNotAvailableSlot?date="+date+"&diaryuserId="+diaryuserid+"&location="+location+"&starttime="+starttime+"&endtime="+endtime+"&slotid="+slotid+"&diaryuser="+diaryuser+" ";

	/*document.getElementById('commencing').value = changedate;
	document.getElementById('caldate').value = changedate;
	
	document.getElementById('location').value = location;
	document.getElementById('diaryUserId').value = diaryuserid;
	document.getElementById('user').value = diaryuser;
	document.getElementById('sTime').value = starttime;
	
	if(editAppointId!=0){
		var apmtDuration = document.getElementById('apmtDuration').value;
		setAppointmentTypeTime(apmtDuration);
	}
	
	$( "#checkavlbltydivpopup" ).modal( "hide" );*/
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setCheckedAppointmentDataRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function setCheckedAppointmentDataRequest(){

if (req.readyState == 4) {
	if (req.status == 200) {
	 
		document.getElementById("allAvailabilitytable").innerHTML = req.responseText;
		
     
     }
}
}

function setSelectedAppointmentData(dataString){
	var temp = dataString.split("/");
	var slotid = temp[0];
	var diaryuserid = temp[1];
	var diaryuser = temp[2];
	var location = temp[3];
	var starttime = temp[4];
	var endtime = temp[5];
	var date = temp[6];
	
	
	
	slotstarttime = starttime;
	
	document.getElementById('date').value = date;
	
	
	var temdate = date.split("-");
	var changedate = temdate[2] + "/" + temdate[1] + "/" + temdate[0];
	

	document.getElementById('commencing').value = changedate;
	document.getElementById('caldate').value = changedate;
	
	document.getElementById('location').value = location;
	document.getElementById('diaryUserId').value = diaryuserid;
	document.getElementById('user').value = diaryuser;
	document.getElementById('sTime').value = starttime;
	document.getElementById('endTime').value = endtime;
	document.getElementById('apmtType').value = "0";
	
	if(editAppointId!=0){
		var apmtDuration = document.getElementById('apmtDuration').value;
		setAppointmentTypeTime(apmtDuration);
	}
	$( "#allAvailabilityPopup" ).modal( "hide" );
	$( "#checkavlbltydivpopup" ).modal( "hide" );
	
}




function checkAvailabilityAjax(){
	
	var avlbltyDate = document.getElementById('avlbltyDate').value;
	var chdiaryUser = document.getElementById('chdiaryUser').value;
	var chlocation = document.getElementById('chlocation').value;
	
	if(chdiaryUser == 0){
		chdiaryUser = "";
	}
	if(chlocation == 0){
		chlocation = "";
	}
	
	var url = "checkAvailableNotAvailableSlot?avlbltyDate="+avlbltyDate+"&chdiaryUser="+chdiaryUser+"&chlocation="+chlocation+" ";


	if (window.XMLHttpRequest) {
			req = new XMLHttpRequest();
		}
		else if (window.ActiveXObject) {
			isIE = true;
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}   
	               
	    req.onreadystatechange = checkAvailabilityAjaxRequest;
	    req.open("GET", url, true); 
	              
	    req.send(null);
	
}

function checkAvailabilityAjaxRequest(){

	if (req.readyState == 4) {
		if (req.status == 200) {
		 
			document.getElementById("checkavlbltytable").innerHTML = req.responseText;
			
	     
	     }
	}
}