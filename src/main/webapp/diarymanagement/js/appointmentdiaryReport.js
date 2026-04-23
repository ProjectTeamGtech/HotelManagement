function showApmtDiaryReport(){

var fromDate = document.getElementById('fromDate').value;
var toDate = document.getElementById('toDate').value;
var diaryUserId = document.getElementById('diaryUser').value;

alert(fromDate);
alert(toDate);
alert(diaryUserId);

var url = "showApmtDiaryReport?fromDate="+fromDate+"&toDate="+toDate+"&diaryUserId="+diaryUserId+"";

   
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showApmtDiaryReportRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function showApmtDiaryReportRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			
			
			
		}
	}
}

function showPreview(){
	validEntry();
	var fromDate = document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var diaryUserId = document.getElementById('diaryUser').value;
	var url = "reportPreviewApmtDiaryReport?fromDate="+fromDate+"&toDate="+toDate+"&diaryUserId="+diaryUserId+"";
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = showPreviewRequest;
    req.open("GET", url, true); 
              
    req.send(null);

}

function showPreviewRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			
			$('#reportPreviewDiv').dialog( "open" );
			
			
		}
	}
}


function validEntry(){
	var fromDate = document.getElementById('fromDate').value;
	var toDate = document.getElementById('toDate').value;
	var diaryUserId = document.getElementById('diaryUser').value;
	var chk = 0;
	document.getElementById('diaryUserError').innerHTML="";
	document.getElementById('fromDateError').innerHTML="";
	document.getElementById('toDateError').innerHTML="";

	if (diaryUserId ==  "0") {
      	var diaryUserId = document.createElement("label");
      	diaryUserId.innerHTML = "Select User";
        document.getElementById('diaryUserError').appendChild(diaryUserId);
        chk=1;
     }
	if (fromDate ==  "") {
      	var fromDate = document.createElement("label");
      	fromDate.innerHTML = "Select From Date";
        document.getElementById('fromDateError').appendChild(fromDate);
        chk=1;
     }
	if (toDate ==  "") {
      	var toDate = document.createElement("label");
      	toDate.innerHTML = "Select To Date";
        document.getElementById('toDateError').appendChild(toDate);
        chk=1;
     }
	
	if(chk==1){
		return false;
	}
	else{
		return true;
	}
	
	
}