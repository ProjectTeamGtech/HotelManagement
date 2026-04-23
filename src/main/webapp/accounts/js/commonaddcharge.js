function createestimate() {
	document.getElementById("estimateclientid").value = ipdclientid;

	var t = 'formtarget';
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	var oldwindow = window.open("", t,
		"status = 1,height = " + openpopupheight + ",width = " + openpopupwidth + ",resizable = 1,scrollbars=yes,left=" + 0
		+ ",top=" + 50);

	oldwindow.focus();



	document.getElementById("estimatefrm").submit();

}


function createdebitestimate() {
	$('#dashboardloaderPopup').modal('show');
	document.getElementById("estimateclientid").value = document.getElementById('clientId').value;

	var t = 'formtarget';
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	var oldwindow = window.open("", t,
		"status = 1,height = " + openpopupheight + ",width = " + openpopupwidth + ",resizable = 1,scrollbars=yes,left=" + 0
		+ ",top=" + 50);

	oldwindow.focus();
	$('#dashboardloaderPopup').modal('hide');


	document.getElementById("estimatefrm").submit();
}


function createopdestimate() {
	document.getElementById("estimateclientid").value = patientId;

	var t = 'formtarget';
	var left = (screen.width / 2) - (700 / 2);
	var top = (screen.height / 2) - (550 / 2);
	var oldwindow = window.open("", t,
		"status = 1,height = " + openpopupheight + ",width = " + openpopupwidth + ",resizable = 1,scrollbars=yes,left=" + 0
		+ ",top=" + 50);

	oldwindow.focus();



	document.getElementById("estimatefrm").submit();
}




function opdcreateinvoicedbl() {
	document.getElementById("opdcreateinvoicebtn").disabled = true;
	document.getElementById('createInvoiceAdditional').submit();
}

function opdcashdeskdbl() {
	document.getElementById("opdcashdeskbtn").disabled = true;
	document.getElementById('casdeskAdditional').submit();
}





function updatetptypetempcharge(dbid,id,val,clientid){
	
	var url = "invnewchargeCompleteApmt?dbid="+dbid+"&charge="+val+"&clientid="+clientid+"";
	
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
    req.onreadystatechange = updatetptypetempchargeRequest;
    req.open("GET", url, true); 
              
    req.send(null);
   
   
}


function updatetptypetempchargeRequest(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var clientId= req.responseText;
			setCashDesk1(clientId);
		}
	}
   
}

function setCashDesk1(clientId){

	//alert(selectedUser);
	//alert(cookiecommencing);
	//alert(cookieSelectedAppointmentid);
//var url = "cashDeskCompleteApmt?selectedUser="+selectedUser+"&date="+cookiecommencing+"&apmtSlotId="+editAppointId+"" ;
//  2018-May-04
var url = "cashDeskBookAppointmentAjax?selectedUser="+clientId+"&apmtSlotId=0" ;

if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		isIE = true;
		req = new ActiveXObject("Microsoft.XMLHTTP");
	}   
               
    req.onreadystatechange = setCashDesk1Request;
    req.open("GET", url, true); 
              
    req.send(null);


} 

function setCashDesk1Request(){
	if (req.readyState == 4) {
		if (req.status == 200) {
			var data=req.responseText;
			  /* document.getElementById("invamtchargeid").innerHTML = req.responseText;
					document.getElementById('chargeTotal').value = document.getElementById('hiddenTotal').value;	
			
//					$( "#preloader" ).modal( "show" );
					if(document.getElementById("yuviloader")){
					document.getElementById("yuviloader").style.display = "none";
					}
			*/
			$('#addchargepopupconsume').modal( "show" );
			document.getElementById("cashDesk31").innerHTML = req.responseText;
		    document.getElementById('chargeTotal3').value = document.getElementById('hiddenTotal').value;
		}
	}
}

